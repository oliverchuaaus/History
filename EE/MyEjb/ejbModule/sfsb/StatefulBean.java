package sfsb;

public interface StatefulBean {
    public void increment();

    public void decrement();

    public int getCount();

    public String getLifecycle();

    public void remove();
}
