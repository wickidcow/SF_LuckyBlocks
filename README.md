# 🍀💥 SF LuckyBlocks Legacy 💥🍀

<p align="center">
  <strong>Break a block. Cross your fingers. Accept your fate.</strong><br>
  Lucky Blocks brought forward for <strong>Slimefun Legacy</strong> and modern Minecraft servers.
</p>

<p align="center">
  <img alt="Minecraft 26.2+" src="https://img.shields.io/badge/Minecraft-26.2%2B-brightgreen?style=for-the-badge">
  <img alt="Slimefun Legacy" src="https://img.shields.io/badge/Slimefun-Legacy-6bd425?style=for-the-badge">
  <img alt="Java 25 build" src="https://img.shields.io/badge/Build-Java%2025-orange?style=for-the-badge">
  <img alt="Build status" src="https://github.com/wickidcow/SF_LuckyBlocks/actions/workflows/maven.yml/badge.svg">
</p>

---

## 🎲 What does it do?

SF LuckyBlocks adds the classic Lucky Blocks experience directly into Slimefun.

Break one and you might get treasure... or you might immediately regret every decision that led you there.

### The blocks

| Block | Luck | What to expect |
|---|---:|---|
| 🍀 **Lucky Block** | 0 | Anything can happen. |
| ✨ **Very Lucky Block** | +80 | Strongly favors the good stuff. |
| ☠️ **Very Unlucky Block** | -80 | You knew what you were doing. |
| 📦 **Pandora's Box** | ??? | Chaos has entered the chat. |

## 💎 Possible surprises

LuckyBlocks includes dozens of random events, including:

- 💰 diamonds, emeralds, iron and valuables
- ⚔️ overpowered lucky weapons and armor
- 🐕 tamed animals and friendly mobs
- 🧪 lucky and unlucky potions
- 🌈 strange sheep, villagers and other weird encounters
- 💣 TNT rain, explosions and flying creepers
- 🧟 hostile mobs, giant slimes and other bad decisions
- 🕳️ traps, cobwebs, water and environmental chaos
- 📦 special Pandora's Box events
- 🛠️ configurable custom item and command surprises

```text
       🟨🟨🟨
       🟨❔🟨    <- surely this one is safe
       🟨🟨🟨
          |
          v
        💥☠️
```

## 🧪 Compatibility

This fork is maintained for the Slimefun Legacy ecosystem.

- ✅ **Minecraft / Paper 26.2+**
- ✅ **Purpur 26.2+** through Paper API compatibility
- ✅ **Folia 26.2+** with `folia-supported: true`
- ✅ **Slimefun Legacy** (`Legacy-SNAPSHOT` API)
- ✅ **Java 25 build environment**
- ✅ Java 21-compatible addon bytecode

> Folia servers must also use a Folia-compatible Slimefun Legacy build. LuckyBlocks itself does not use the old Bukkit scheduler.

## 📥 Download

Every successful build on the main branch publishes a **raw `.jar` file** to GitHub Releases.

No `.jar.zip` extraction step is required.

Expected filename:

```text
SF_LuckyBlocks_Legacy_v1.0.0.jar
```

Open the repository's **Releases** section and download the `.jar` asset directly into your server's `plugins` folder.

## 🛠️ Building

The project builds with Maven using Java 25 while targeting Java 21-compatible addon bytecode.

```bash
mvn -B -ntp clean package
```

The resulting plugin is written to:

```text
target/SF_LuckyBlocks_Legacy_v1.0.0.jar
```

## ⚙️ Configuration

Lucky Blocks can naturally appear during chunk population. The default configuration lets you control the spawn chance and blacklist worlds such as the Nether and End.

You can also define custom surprises with:

- vanilla items
- Slimefun items
- enchantments
- console commands
- Lucky / Neutral / Unlucky / Pandora luck levels

## 🧪 Server admins

Recommended installation:

1. Install a current **Slimefun Legacy** build.
2. Run **Paper, Purpur or Folia 26.2+**.
3. Drop `SF_LuckyBlocks_Legacy_v1.0.0.jar` into `plugins/`.
4. Restart the server.
5. Search **Lucky Blocks** in the Slimefun Guide.
6. Craft one and make questionable choices. 🍀

## ❤️ Credits & legacy

This project exists because of the original **LuckyBlocks (Slimefun Edition)** work by **TheBusyBiscuit** and the Slimefun community.

A special thank-you also goes to **[iiLuckyDev](https://github.com/iiLuckyDev)**. This fork was created from **[iiLuckyDev/luckyblocks](https://github.com/iiLuckyDev/luckyblocks)**, whose 1.21.11 modernization brought the classic addon forward with updated Paper, enchantment, potion-effect, and entity APIs and provided the strongest modern foundation for this Slimefun Legacy continuation.

SF LuckyBlocks Legacy builds on both of those efforts: preserving the original gameplay and identity while continuing the compatibility work for **Paper, Purpur and Folia 26.2+** with **Slimefun Legacy**.

Modern Slimefun Legacy maintenance: **wickidcow**.

## 📜 License

SF LuckyBlocks Legacy remains open source under the **GNU General Public License v3.0**, matching the original project.

---

<p align="center">
  🍀 <strong>Sometimes diamonds. Sometimes TNT. Always LuckyBlocks.</strong> 💥
</p>
