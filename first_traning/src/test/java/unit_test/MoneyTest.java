package test.java.unit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import unit_test.Money;

public class MoneyTest {

    @Test
    public void testEqualsSameInstance() {
        Money money = new Money(1);
        assertTrue(money.equals(money));
    }

    @Test
    public void testEqualsSameValue() {
        Money money1 = new Money(1);
        Money money2 = new Money(1);
        assertTrue(money1.equals(money2));
    }

    @Test
    public void testEqualsDifferentValue() {
        Money money1 = new Money(1);
        Money money2 = new Money(2);
        assertFalse(money1.equals(money2));
    }

    @Test
    public void testEqualsNull() {
        Money money = new Money(1);
        assertFalse(money.equals(null));
    }

    @Test
    public void testEqualsDifferentType() {
        Money money = new Money(1);
        assertFalse(money.equals("1"));
    }

    @Test
    public void testAdd() {
        Money money1 = new Money(1);
        Money money2 = new Money(2);
        money1.add(money2);
        assertTrue(money1.equals(new Money(3)));
    }

    @Test
    public void testAddSameInstance() {
        Money money = new Money(1);
        money.add(money);
        assertTrue(money.equals(new Money(2)));
    }

    @Test
    public void testAddZero() {
        Money money = new Money(1);
        money.add(new Money(0));
        assertTrue(money.equals(new Money(1)));
    }

    @Test
    public void testEqualsSymmetry() {
        Money money1 = new Money(5);
        Money money2 = new Money(5);
        assertTrue(money1.equals(money2));
        assertTrue(money2.equals(money1));
    }
}
