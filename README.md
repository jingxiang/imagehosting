### Introduction

ImageHosting是一个集合多家免费图床的JAVA实现

### Guides

1、添加依赖

```
<dependency>
    <groupId>com.bushangbuxia</groupId>
    <artifactId>imagehosting</artifactId>
    <version>1.0.0</version>
</dependency>
```
最新版本：[https://search.maven.org/artifact/com.bushangbuxia/imagehosting](https://search.maven.org/artifact/com.bushangbuxia/imagehosting)

2、使用案例

```java
public static void main(String[] args) {
	try {
		ImageHostingService imageHostingService = new DefaultImageHostingService();
		File imageFile = new File("C:\\5E44465978FB976E3597D3110CB76AB9.png");
		ImageHostingOptions options = new ImageHostingOptions();
		options.setHostingPlatforms(Arrays.asList(ImageHostingPlatform.JD));
		ImageHostingResponse response = imageHostingService.upload(imageFile, options);
		System.out.println(JSON.toJSONString(response));
	} catch (Exception e) {
		e.printStackTrace();
	}
}
```

### Support Site

当前支持的站点

- 苏宁
- 京东
- 阿里
- 今日头条
- 网易
- 搜狐
- CC
- 掘金

相关站点正在陆续加入中，欢迎贡献pull request......

### License

ImageHosting is released under the [MIT license](https://github.com/jingxiang/imagehosting/blob/master/LICENSE).





