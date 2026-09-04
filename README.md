# Respawn!Reborn! (RespawnReborn) — 1.12.2 Cleanroom port

Port of [yiyuyan/RespawnReborn](https://github.com/yiyuyan/RespawnReborn) (1.21 Fabric/NeoForge) to Minecraft 1.12.2 [CleanroomMC](https://cleanroommc.com), based on the [CleanroomModTemplate](https://github.com/CleanroomMC/CleanroomModTemplate).

## Features

- **Safe respawn**: when you respawn at your bed, the spawn position is checked and relocated to the nearest safe spot — never inside blocks, liquids or cauldrons. Position is scattered randomly (2–4 blocks) to avoid instant suffocation/drowning.
- **Death coordinates**: on death, a chat message shows your death position; clicking it runs `/tp @s x y z` to teleport you back.

## Building

Requires JDK 25 (auto-provisioned by Gradle toolchains).

```bash
./gradlew build
```

Output jar: `build/libs/rr-<version>-dev.jar` (dev) and `build/libs/rr-<version>.jar` (remapped).

Run client/server: `./gradlew runClient` / `./gradlew runServer`.

## Port notes

- Respawn position: upstream injects `ServerPlayer.findRespawnAndUseSpawnBlock`; in 1.12.2 the equivalent single caller is `EntityPlayer.getBedSpawnLocation` (used by `PlayerList.recreatePlayerEntity` for death respawn, end exit and login respawn), so the mixin targets that.
- Death message: upstream injects `ServerPlayer.die`; in 1.12.2 that is `EntityPlayerMP.onDeath`.
- Mixin classes: `cn.ksmcbrigade.rr.mixin.PlayerMixin` (respawn), `cn.ksmcbrigade.rr.mixin.PlayerMPMixin` (death).

## License

MIT (same as upstream).
