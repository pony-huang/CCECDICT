# [CCECDICT](https://github.com/PonKing66/CCECDICT)


![Build](https://github.com/PonKing66/CCECDICT/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/22313.svg)](https://plugins.jetbrains.com/plugin/22313)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/22313.svg)](https://plugins.jetbrains.com/plugin/22313)

<!-- Plugin description -->
基于 IntelliJ IDEA 的英汉词典代码补全插件。这个智能代码补全插件在编写代码时提供智能的中英文翻译代码补全功能，可以纠正单词拼写错误。

插件支持 PyCharm、Golang 和 IntelliJ Idea。你可以在插件设置中配置 [EEECDICT](https://github.com/PonKing66/CCECDICT)。该插件使用了庞大的 ECDICT 单词数据库，但只保留了[最常见的 10,000 个英语单词](https://github.com/first20hours/google-10000-english)。如果需要更多的教程，请访问 [这里](https://github.com/PonKing66/CCECDICT)。

<!-- Plugin description end -->

安装方式有两种：
- 使用 IDE 内置的插件系统：在设置中找到“插件” > “市场” > 搜索“EEECDICT” > 安装插件；
- 手动安装：下载[最新版本](https://github.com/PonKing66/dictionary-completion/releases/latest)，然后在设置中手动安装插件。

---

<img src="example.gif"  style="text-align: center"  width="426"  alt="images"/>

**注意：** ECDICT 单词库庞大，插件仅保存在[10000个最常见英语单词的列表](https://github.com/first20hours/google-10000-english)，另有需求请自行配置[自定义数据库](https://github.com/PonKing66/CCECDICT)。

配置自定义数据库：
- [下载 ecdict-sqlite](https://github.com/skywind3000/ECDICT/releases/download/1.0.28/ecdict-sqlite-28.zip)，配置字典库路径。

<img src="img.jpg"  style="text-align: center"  width="458"  height="524.5" alt="images"/>

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation

## Thanks

- [ECDICT](https://github.com/skywind3000/ECDICT.git) @skywind3000