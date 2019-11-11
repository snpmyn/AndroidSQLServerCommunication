<div align=center><img src="https://github.com/snpmyn/AndroidSQLServerCommunication/raw/master/image.png"/></div>

[![SNAPSHOT](https://jitpack.io/v/Jaouan/Revealator.svg)](https://jitpack.io/#snpmyn/AndroidSQLServerCommunication)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/120d786533f040e68a79b6cf2a734d1d)](https://www.codacy.com/manual/snpmyn/AndroidSQLServerCommunication?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=snpmyn/AndroidSQLServerCommunication&amp;utm_campaign=Badge_Grade)
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)
[![API](https://img.shields.io/badge/API-19%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=19)

### 介绍
Android客户端与SQL Server通信。

### 架构

| 模块 | 说明 | 补充 |
|:-:|:-:|:-:|
| 示例app | 用法举例 | 无 |
| 一方库Library | 功能实现 | 无 |

### 依赖、权限

| 模块 | 依赖 |
|:-:|:-:|
| 示例app | implementation project(path: ':library') |
| 一方库Library | 无 |

| 模块 | 权限 |
|:-:|:-:|
| 示例app | android:name="android.permission.INTERNET" |
| 一方库Library | 无 |

### 使用
build.gradle(module)
```
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {  
    repositories {
        google()
        jcenter()
                
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.5.2'           

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}
```
build.gradle(app)
```
apply plugin: 'com.android.application'

android {
    ...
    defaultConfig {
        ...      
    }       
    configurations.all {
        resolutionStrategy.cacheChangingModulesFor 0, 'seconds'
    }
}

dependencies {
    implementation 'com.github.snpmyn:AndroidSQLServerCommunication:master-SNAPSHOT'
}
```

### License
```
Copyright 2019 snpmyn

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
