package com.epam.ik;

import org.junit.*;


public class QuadraticEquationTest {

    QuadraticEquation quadraticEquation;

    @Before
    public void setQuadraticEquation() {
        this.quadraticEquation = new QuadraticEquation();
    }

    @Test
    public void calcNumOneIsZero() {
        quadraticEquation.setNumOne(0);
        quadraticEquation.setNumTwo(4);
        quadraticEquation.setNumThree(1);
        Assert.assertNull(quadraticEquation.calcEquation());
        System.out.println("no roots");
    }

    @Test
    public void calcDiscriminantZero() {
        quadraticEquation.setNumOne(2);
        quadraticEquation.setNumTwo(4);
        quadraticEquation.setNumThree(2);
        Assert.assertArrayEquals(new double[]{-1}, quadraticEquation.calcEquation(), 0);
        System.out.println("one exiting root");
    }

    @Test
    public void calcDiscriminantPositive() {
        quadraticEquation.setNumOne(2);
        quadraticEquation.setNumTwo(4);
        quadraticEquation.setNumThree(0);
        Assert.assertArrayEquals(new double[]{0, -2}, quadraticEquation.calcEquation(), 0);
        System.out.println("two existing roots");
    }

    @Test
    public void calcDiscriminantNegative() {
        quadraticEquation.setNumOne(5);
        quadraticEquation.setNumTwo(1);
        quadraticEquation.setNumThree(1);
        Assert.assertNull(quadraticEquation.calcEquation());
        System.out.println("no roots");
    }

}