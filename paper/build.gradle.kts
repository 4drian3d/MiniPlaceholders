plugins {
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
    id("xyz.jpenilla.run-paper") version "1.0.6"
}

dependencies {
    compileOnly(project(":miniplaceholders-common"))
    compileOnly(project(":miniplaceholders-api"))
    compileOnly(project(":miniplaceholders-connect"))
    compileOnly("io.papermc.paper:paper-api:1.19.1-R0.1-SNAPSHOT")
    compileOnly("io.papermc.paper:paper-mojangapi:1.19.1-R0.1-SNAPSHOT")
}

val pluginVersion: String = version as String

bukkit {
    name = "MiniPlaceholders"
    main = "me.dreamerzero.miniplaceholders.paper.PaperPlugin"
    apiVersion = "1.18"
    website = "https://github.com/4drian3d/MiniPlaceholders"
    authors = listOf("4drian3d")
    version = pluginVersion
    commands {
        register("miniplaceholders") {
            description = "MiniPlaceholders Command"
            permission = "miniplaceholders.command"
        }
    }
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()

        options.release.set(17)
    }
    runServer {
        minecraftVersion("1.19.1")
    }
}

