# Mill Native Client Port

There are currently 3 branches of interest in this repository.

# mill_original

This branch is a direct port of the original Mill codebase. It is a "reference" implementation if you will.

In terms of porting to scala native, it has some problems.
- A reliance on the JVM SHA1 implementation
- A reliance on junit
- Some other oddballs.

Passes tests though.

# utest_lift

This branch ported (in order) the following:

1. Unit tests to utest
2. The SHA1 implementation replaced with `pt.kcry::sha::2.0.2`
3. The junit dependency removed
4. The mill codebase itself (through the magic of chatGPT) rewritten into the closest scala code that followed the original java implementation

This branch passes the tests on the JVM. This makes me believe a pure scala port of the mill client is feasible.

```
git checkout utest_lift
scala-cli test .
```

# scala_native

This branch _attempts_ to switch the _platform_ to scala native. I'm stuck on a couple of things:

1. I can't get resources working. I'm unclear if that's a scala-cli thing or a me thing.
2. I don't _think_ I've made other tests to the `FileToStreamTailerTest` implementation or test, but it now failes on native. Some work. I believe that (if resource were free and unlimited) this would be expected to work on native?
3. The other tests are commented out until such time as I can jump the first two hurdles.


```
git checkout native
scala-cli test .
```



