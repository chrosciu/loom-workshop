package eu.chrost.scopedvalues;

public class S01Rebinding {
    private static final ScopedValue<String> X = ScopedValue.newInstance();
    public static void main(String[] args) {
        foo();
    }

    static void foo() {
        //System.out.println(X.get());
        ScopedValue.where(X, "hello").run(S01Rebinding::bar);
        //System.out.println(X.get());
    }

    static void bar() {
        System.out.println(X.get());
        ScopedValue.where(X, "goodbye").run(S01Rebinding::baz);
        System.out.println(X.get());
    }

    static void baz() {
        System.out.println(X.get());
    }
}
