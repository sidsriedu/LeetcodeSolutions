package org.leetcode.solutions.EvaluateDivision;

import java.util.*;

class Solution {

    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList("a","b"));
        equations.add(Arrays.asList("b","c"));

        double[] values = {2.0,3.0};

        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("a","c"));
        queries.add(Arrays.asList("b","a"));
        queries.add(Arrays.asList("a","e"));
        queries.add(Arrays.asList("a","a"));
        queries.add(Arrays.asList("x","x"));

        Solution solution = new Solution();
        double[] result = solution.calcEquation(equations, values, queries);
        System.out.println("Result : "+ Arrays.toString(result));


    }

    double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = createGraph(equations, values);    // {a={b=2.0}, b={a=0.5, c=3.0}, c={b=0.3333333333333333}}
        double[] result = new double[queries.size()];
        for(int i=0; i<queries.size(); i++) {
            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);

            if(start.equals(end)){
                if(graph.containsKey(start)) {
                    result[i] = 1;
                } else {
                    result[i] = -1;
                }
            } else {
                Set<String> visited = new HashSet<>();
                result[i] = dfs(graph, start, end, visited);
            }
        }
        return result;
    }

    private Map<String, Map<String, Double>> createGraph(List<List<String>> equations, double[] values) {
        Map<String, Map<String, Double>> graph = new HashMap();
        for(int i=0; i<equations.size(); i++) {

            String start = equations.get(i).get(0);
            String end = equations.get(i).get(1);
            double val = values[i];

            graph.putIfAbsent(start, new HashMap());
            graph.get(start).put(end, val);

            graph.putIfAbsent(end, new HashMap());
            graph.get(end).put(start, 1/val);
        }
        return graph;
    }

    private double dfs(Map<String, Map<String, Double>> graph, String start, String end, Set<String> visited) {
        if(!graph.containsKey(start))
            return -1;
        if(graph.get(start).containsKey(end))
            return graph.get(start).get(end);
        visited.add(start);
        Map<String, Double> startMap = graph.get(start);
        for(Map.Entry<String, Double> entry : startMap.entrySet()){
            if(!visited.contains(entry.getKey())){
                double current = dfs(graph, entry.getKey(), end, visited);
                if(current!=-1)
                    return current*entry.getValue();
            }
        }
        return -1;
    }

}
