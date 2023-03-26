package gre.lab1.groupe12;

import gre.lab1.gui.MazeGenerator;
import gre.lab1.gui.MazeBuilder;
import gre.lab1.gui.Progression;

import java.util.Collections;
import java.util.List;

/**
 * Implémente l'interface MazeGenerator en définissant une fonction de génération de labyrinthe grâce à l'exploration
 * en profondeur.
 */
public final class DFSMazeGenerator implements MazeGenerator {
  @Override
  public void generate(MazeBuilder builder, int from) {
    // Pseudo-code utilisé :  https://fr.wikipedia.org/wiki/Algorithme_de_parcours_en_profondeur
    explore(builder, from);
  }

  /**
   * Fonction auxiliaire récursive à l'exploration en profondeur.
   * @param builder Un builder à qui déléguer les modifications de la structure de données.
   * @param from Sommet de départ
   */
  private void explore(MazeBuilder builder, int from) {
    builder.progressions().setLabel(from, Progression.PROCESSING);
    List<Integer> n = builder.topology().neighbors(from);
    Collections.shuffle(n);
    for (int vertex : n) {
      if (builder.progressions().getLabel(vertex) == Progression.PENDING) {
        builder.removeWall(from, vertex);
        explore(builder, vertex);
      }
    }
    builder.progressions().setLabel(from, Progression.PROCESSED);
  }
}
