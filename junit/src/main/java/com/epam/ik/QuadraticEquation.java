package com.epam.ik;

public class QuadraticEquation {
    private double numOne;
    private double numTwo;
    private double numThree;

    public QuadraticEquation() {
        //
    }

    public QuadraticEquation(double numOne, double numTwo, double numThree) {
        this.numOne = numOne;
        this.numTwo = numTwo;
        this.numThree = numThree;
    }

    public double[] calcEquation() {
        double[] roots;
        double discriminant = ((numTwo * numTwo) - (4 * numOne * numThree));

        if (numOne != 0 && discriminant > 0) {
            roots = new double[2];
            double res = Math.sqrt((numTwo * numTwo) - (4 * numOne * numThree));

            roots[1] = (-numTwo - res) / (2 * numOne);
            roots[0] = (-numTwo + res) / (2 * numOne);
        }
        else if (numOne != 0 && discriminant == 0) {
            roots = new double[1];
            roots[0] = -numTwo / (2 * numOne);
        }
        else {
            System.out.println("No roots!!!");
            roots = null;
        }
        return roots;
    }

    public void setNumOne(double numOne) {
        this.numOne = numOne;
    }

    public void setNumTwo(double numTwo) {
        this.numTwo = numTwo;
    }

    public void setNumThree(double numThree) {
        this.numThree = numThree;
    }
}