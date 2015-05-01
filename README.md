SimpleAB - A simple framework for A/B tests
=============================

SimpleAB is a framework to help you A/B test your Android applications with no hassle.
A/B testing should be as simple as it needs to, and if you're not interested in handling huge SDKs and understanding complex dashboards and documentation articles, SimpleAB is the tool you was looking for.

SimpleAB will make A/B tests as easy to run as to write a one-liner like:
```java
SimpleAB.with(context).perform(abTest).now();
```
- - - 
Usage
--------

#### Defining Tests

A test is defined through an ID by a class extending `AbstractABTest`:
```java
AbstractABTest firstTest = new MyFirstABTest(new ABTestVariant() {
  @Override
  public void perform() {
    // do something
  }
  @Override
  public String getId() {
    return "A";
  }
}, new ABTestVariant() {
  @Override
  public void perform() {
    // do something else
  }
  @Override
  public String getId() {
    return "B";
  }
});
```

Note that you can define as many variants as you want, as long as you define different IDs for them.
The IDs, by themselves can be any `String` you want, as long as they are not `equals()`.

#### Drawers

A `Drawer` is responsible for selecting which `ABTestVariant` will be executed. The library has a default `Drawer` built-in, that leverages the usual java `Random` implementation for pseudo-randomic choosing.

If you want, you can also define your own class that `implements ABTestDrawer`. You can then define any strategy to select test variants, for example by defining weights for each variant (this example is implemented in the `sample` project of the library), or by taking in account the IDs of the variants.

#### Listener for callback

It's also optional to define an `ABTestListener` so you can have a callback after a variant is drawn. To do that, simply define a class that implements the interface and override its methods. The callback method will be executed after the A/B test runs. This way, you can perform any subsequent task to the test, like `POST`-ing the result to a server, for example.

The API will then be something like:
```java
SimpleAB.with(context).perform(abTest).withDrawer(variantDrawer).listener(listener).now();
```

#### Persistence of chosen variants

By default, the library persists the chosen variants in `SharedPreferences`. This way, once your app runs for the first time and the variants are chosen by the `Drawer`, the app state regarding your A/B tests will stay the same for the subsequent runs (unless the user *`clears app data`*).

#### Debugging

If you are debugging your application and want to avoid the need to clear the app data to re-draw your A/B tests variants, you can simply call
```java
SimpleAB.with(this).alwaysDrawVariants();
```
before performing your A/B tests. This will bypass the persisting step of the library, and by that, the system will always `draw()` variants based on your `Drawer`.
- - - 
Download
--------

Right now the library is not yet available through gradle, but this is in the roadmap. For now, just checkout the directory folder and add it as a module to your project, or **download the *[aar][1]*** from the repository adding it to the `libs` directory in your project.

Then, add this to your `build.gradle` dependencies:

```groovy
compile 'br.com.zynger.simpleab:simpleab:1.0@aar'
```

And this to your `repositories` node in the top-level `build.gradle`:
```groovy
flatDir {
  dirs 'libs'
}
```



License
-------

    Copyright 2015 Julio Zynger, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    
 [1]: https://github.com/julioz/simpleab/blob/master/sample/app/libs/simpleab.aar