package com.dkit.oop.sd2.BusinessObjects;

/**
 * This interface defines a method named matches(), and we can add classes that implement this
 * interface and override matches() to match objects based on specific fields.
 *
 * @author Dermot Logue- DkIT
 * Reference Link: https://github.com/logued/oop-ifilter-sample
 *
 */

public interface IFilter {
    public boolean matches(Object other);
}
