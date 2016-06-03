# Cesium WebStorm Plugin

Created by the [Cesium team](http://cesiumjs.org/) for Cesium development in [WebStorm](https://www.jetbrains.com/webstorm/).<br/>

<a href="http://cesiumjs.org/"><img src="images/cesium.jpg" height="40" /></a>

## Add The Plugin

1. Navigate to WebStorm settings
2. Click Plugins
3. Click Browse repositories...
4. Search for `Jump Source Spec`
5. Click Install

With this plugin, you can jump between source and specs files with a simple press of `alt + shift + k`.

![](images/jss.gif)


## Build Guide

1. Install [IntelliJ Community Edition](https://www.jetbrains.com/idea/#chooseYourEdition)
2. Run `git clone git@github.com:AnalyticalGraphicsInc/cesium-webstorm-plugin.git`
3. Navigate to `File` `->` `Project Structure...`
4. Under the `Project` tab click `New` and select `IntelliJ Platform Plugin SDK`
5. Browse to where you installed IntelliJ, similar to:
   - Mac: `/Applications/IntelliJ IDEA CE.app`
   - Windows: `C:\\Program Files (x86)\JetBrains\IntelliJ IDEA Community Edition`
6. Press `Ok` and then `Apply`
7. Navigate to `Run` `->` `Edit Configurations...`
8. Click the `+` icon and select `Plugin`, give it a name and you are good to go

Now if you navigate to `Run` `->` `Debug` a new instance of IntelliJ will open with the plugin installed.

For more information on developing IntelliJ plugins check [this](https://www.jetbrains.com/help/idea/2016.1/plugin-development-guidelines.html) out.

## Contributions

Pull requests are appreciated! Please use the same [Contributor License Agreement (CLA)](https://github.com/AnalyticalGraphicsInc/cesium/blob/master/CONTRIBUTING.md) used for [Cesium](http://cesiumjs.org/).
