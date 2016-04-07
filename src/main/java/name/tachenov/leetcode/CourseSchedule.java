/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author alqualos
 */
public class CourseSchedule {
    private static final int NODE_NOT_VISITED = 0;
    private static final int NODE_ON_PATH = 1;
    private static final int NODE_VISITED = 2;
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[][] prereqs = new int[numCourses][numCourses + 1];
        for (int[] prereq : prerequisites) {
            prereqs[prereq[0]][++prereqs[prereq[0]][0]] = prereq[1];
        }
        int[] marks = new int[numCourses];
        int[] courses = new int[numCourses];
        MutableInt taken = new MutableInt();
        for (int i = 0; i < numCourses; ++i) {
            if (marks[i] == NODE_NOT_VISITED && !tarjanSort(prereqs, i, marks, courses, taken)) {
                return new int[0];
            }
        }
        return courses;
    }
    
    private static boolean tarjanSort(int[][] prereqs, int course,
                                      int[] marks, int[] courses, MutableInt done) {
        int[] children = prereqs[course];
        marks[course] = NODE_ON_PATH;
        for (int i = 0; i < children[0]; ++i) {
            int child = children[i + 1];
            if (marks[child] == NODE_ON_PATH
                    || marks[child] == NODE_NOT_VISITED
                        && !tarjanSort(prereqs, child, marks, courses, done)) {
                return false;
            }
        }
        marks[course] = NODE_VISITED;
        courses[done.value++] = course;
        return true;
    }
    
    private static class MutableInt {
        int value;
    }
    
    public static void main(String[] args) {
        CourseSchedule cs = new CourseSchedule();
        System.out.println(Arrays.toString(cs.findOrder(2, new int[][] {{0, 1}})));
        System.out.println(Arrays.toString(cs.findOrder(10, LeetCode.array2d("[[5,8],[3,5],[1,9],[4,5],[0,2],[1,9],[7,8],[4,9]]"))));
    }
}
