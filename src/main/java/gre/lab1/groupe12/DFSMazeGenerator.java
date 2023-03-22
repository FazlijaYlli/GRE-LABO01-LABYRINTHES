// TODO: refactor le nom du package groupeX avec le bon numéro de groupe (SHIFT + F6)
package gre.lab1.groupe12;

import gre.lab1.gui.MazeGenerator;
import gre.lab1.gui.MazeBuilder;
import gre.lab1.gui.Progression;

import java.util.Collections;
import java.util.List;

// TODO: javadoc
public final class DFSMazeGenerator implements MazeGenerator {
  @Override
  public void generate(MazeBuilder builder, int from) {
    // TODO: A implémenter
    //  NOTES D'IMPLÉMENTATION :
    //  Afin d'obtenir l'affichage adéquat, indiquer la progression (en tant que label du sommet traité) :
    //  - PROCESSING, en pré-traitement;
    //  - PROCESSED, en post-traitement.
    //  Le labyrinthe n'a que des murs au début de la construction, il faut donc créer les passages en
    //  supprimant des murs.

    // ai essayé d'implémenter https://fr.wikipedia.org/wiki/Algorithme_de_parcours_en_profondeur
    while (builder.topology().vertexExists(from)) {
      if (builder.progressions().getLabel(from) == Progression.PENDING) {
        explore(builder, from++);
      }
    }
  }

  private void explore(MazeBuilder builder, int from) {
    builder.progressions().setLabel(from, Progression.PROCESSING);
    List<Integer> n = builder.topology().neighbors(from);
    Collections.shuffle(n);
    for (int vertex : n) {
      builder.removeWall(from, vertex);
      if (builder.progressions().getLabel(vertex) == Progression.PENDING) {
        explore(builder, vertex);
        builder.progressions().setLabel(vertex, Progression.PROCESSED);
      }
    }
  }
}
