import graph.Graph;

public class GraphSearch {

    public static void main(String[] args) {
        new GraphSearch();
    }

    GraphSearch() {
        Graph<String> graph = new Graph<>();

        String frankfurt = "Frankfurt";
        String mannheim = "Mannheim";
        String karlsruhe = "Karlsruhe";
        String augsburg = "Augsburg";
        String wuerzburg = "Würzburg";
        String erfurt = "Erfurt";
        String stuttgart = "Stuttgart";
        String nuernberg = "Nürnberg";
        String kassel = "Kassel";
        String muenchen = "München";

        graph.add(frankfurt);
        graph.add(mannheim);
        graph.add(karlsruhe);
        graph.add(augsburg);
        graph.add(wuerzburg);
        graph.add(erfurt);
        graph.add(stuttgart);
        graph.add(nuernberg);
        graph.add(kassel);
        graph.add(muenchen);
        
        graph.addConnection(frankfurt, mannheim, 85);
        graph.addConnection(mannheim, karlsruhe, 80);
        graph.addConnection(karlsruhe, augsburg, 250);
        graph.addConnection(augsburg, muenchen, 84);
        graph.addConnection(frankfurt, wuerzburg, 217);
        graph.addConnection(wuerzburg, erfurt, 186);
        graph.addConnection(wuerzburg, nuernberg, 103);
        graph.addConnection(nuernberg, stuttgart, 183);
        graph.addConnection(nuernberg, muenchen, 167);
        graph.addConnection(frankfurt, kassel, 173);
        graph.addConnection(kassel, muenchen, 502);

        System.out.println(graph.getShortestPath(frankfurt, muenchen));
        System.out.println(graph.getCheapestPath(frankfurt, muenchen));
    }

}
