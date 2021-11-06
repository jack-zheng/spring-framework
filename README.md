# spring-framework-5.2.x

提示：构建源码之前，将你系统中的 GRADLE_HOME 设置直接删除，使用项目指定的 gradle 构建，不然各种出问题

1. 下载源码
2. 修改 build.gradle 和 settings.gradle 再源中添加国内源
3. cd 到 spring-framework 文件夹下运行 `gradlew :spring-oxm:compileTestJava` 和 `gradlew :spring-core:compileTestJava` 预编译比较模块
4. 打开 idea -> New -> New project from existing source -> 选则 spring-framework 下的 build.gradle 文件
5. 等待自动构建，终端会出现 gradle 构建成功提示
6. 新建 gradle module 'spring-debug' 用于测试

## 测试遇到的问题

添加依赖

```gradle
// 方便测试
compileOnly group: 'org.projectlombok', name: 'lombok', version: '1.18.20'
implementation(project(":spring-context"))
```

运行失败，抛错

```txt
...spring-framework\spring-core\src\main\java\org\springframework\core\ReactiveAdapterRegistry.java
Error:(346, 51) java: 找不到符号
  符号:   变量 CoroutinesUtils
  位置: 类 org.springframework.core.ReactiveAdapterRegistry.CoroutinesRegistrar
```

在 idea 左侧找到 'spring-core/kotlin-coroutines/build/libs/kotlin-coroutines-5.2.18.RELEASE.jar' 文件，右键 Add as Library, OK.

然后顶部选择 Build -> Rebuild Project. 之后还是失败了，提示 AOP 相关的类编译失败, 参考[csdn](https://blog.csdn.net/qq_38762237/article/details/107815524).

## Gradle zip 准备

步骤 3 一开始会下载对应的 gradle 包，如果下载很慢，可以先查看根目录下 gradle/wrapper/gradle-wrapper.properties 中 gradle 版本，去官网下载一个。

然后修改 properties 中的配置，直接指向本地文件

```config
-- windows 配置
distributionUrl=file:///C:/Users/jack/Downloads/gradle-5.6.3-all.zip
```


