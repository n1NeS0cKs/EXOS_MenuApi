# EXOS_MenuApi 
___
Это api предназначенное для упрощения написания меню с помощью кода.

## Примеры реализации меню
___ 
### Одностраничное меню:
```java
public class TestMenu extends AbstractMenu {

    ButtonStack sayHelloButton = new ButtonStack(Material.STONE,"sayHello");

    public TestMenu(List<ItemStack> itemStacks) {
        super(54, Component.text("test"));
    }

    @Override
    protected void initializeItems() {
        getInventory().setItem(0,sayHelloButton);

    }

    @Override
    protected void initializeActions() {
        getActionMap().put("sayHello", this::sayHello);
    }

    private void sayHello(InventoryClickEvent event) {
        Bukkit.broadcastMessage("Hello World!");
    }
}
```

### Многостраничное меню:
```java
public class TestMenu extends AbstractPagedMenu {

    ButtonStack nextPageStack = new ButtonStack(Material.GREEN_DYE,"nextPage");
    ButtonStack previousPageStack = new ButtonStack(Material.RED_DYE, "previousPage");

    public TestMenu(List<ItemStack> itemStacks) {
        super(54, Component.text("test"), itemStacks);
    }

    @Override
    protected void initializeItems() {
        getInventory().setItem(48,previousPageStack);
        getInventory().setItem(50,nextPageStack);
        
    }

    @Override
    protected void initializeActions() {
        getActionMap().put("nextPage", event -> nextPage());
        getActionMap().put("previousPage", event -> previousPage());
    }
}
```
## Добавление зависимости
___
### Maven
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>   
```
```xml
<dependency>
    <groupId>com.github.n1NeS0cKs</groupId>
    <artifactId>EXOS_MenuApi</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle
```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
```
```groovy
dependencies {
    implementation 'com.github.n1NeS0cKs:EXOS_MenuApi:1.0.0'
}
```



