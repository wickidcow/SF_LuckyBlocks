<div align="center">

# 🍀💥 LuckyBlocks — Slimefun Legacy

**Break a block. Cross your fingers. Accept your fate.**

![Slimefun Legacy](https://img.shields.io/badge/Slimefun-Legacy-6bd425?style=for-the-badge)
![Paper 26.2+](https://img.shields.io/badge/Paper-26.2%2B-blue?style=for-the-badge)
![Purpur 26.2+](https://img.shields.io/badge/Purpur-26.2%2B-bb66ff?style=for-the-badge)
![Folia 26.2+](https://img.shields.io/badge/Folia-26.2%2B-6e45e2?style=for-the-badge)
![Java 25](https://img.shields.io/badge/Build-Java%2025-orange?style=for-the-badge)
![License: GPL-3.0](https://img.shields.io/badge/License-GPL--3.0-blue?style=for-the-badge)
![Maintained for AlbionMC.com](https://img.shields.io/badge/Maintained%20for-albionmc.com-7b68ee?style=for-the-badge)

</div>

> [!IMPORTANT]
> LuckyBlocks Legacy is an **unofficial community maintenance fork** for Slimefun Legacy, developed for use on **albionmc.com** while preserving the classic LuckyBlocks experience and project history.

## 🎲 What does LuckyBlocks do?

LuckyBlocks puts chaotic, randomized reward blocks directly into Slimefun. Break one and you might get treasure, mobs, gear, potions, explosions, traps, or something much stranger.

| Block | Luck | What to expect |
|---|---:|---|
| 🍀 **Lucky Block** | 0 | Anything can happen. |
| ✨ **Very Lucky Block** | +80 | Strongly favors good outcomes. |
| ☠️ **Very Unlucky Block** | -80 | You knew what you were doing. |
| 📦 **Pandora's Box** | ??? | Chaos has entered the chat. |

Possible surprises include valuables, lucky weapons/armor, tamed animals, potions, strange mobs, TNT, hostile encounters, environmental traps, Pandora events, and configurable custom item/command surprises.

```text
       🟨🟨🟨
       🟨❔🟨   <- surely this one is safe
       🟨🟨🟨
          |
          v
        💥☠️
```

## 🧪 Slimefun Legacy maintenance

- ✅ **Minecraft / Paper 26.2+**
- ✅ **Purpur 26.2+** through Paper API compatibility
- ✅ **Folia 26.2+** with dedicated Folia API compilation and region-aware runtime fixes
- ✅ **Slimefun Legacy** as the primary Slimefun target
- ✅ **Java 25** build environment with Java 21-compatible addon bytecode
- ✅ classic LuckyBlocks item IDs, luck categories and gameplay preserved
- ✅ raw, directly usable `.jar` output from GitHub Actions and GitHub Releases

Folia use still requires the rest of the installed Slimefun/addon stack to be Folia-safe too; one addon cannot make an incompatible dependency region-thread safe by itself.

## 🔧 v1.0.1 runtime hardening

- fixes natural Lucky Block placement so it spawns **above** the highest solid surface instead of testing the surface block as though it were air;
- moves natural-generation world access onto the region that owns the generated chunk;
- makes the Void Hole dig to the world's real minimum height instead of stopping at legacy Y=0;
- groups multi-block trap writes by owning chunk/region for Folia safety;
- fixes the fake-diamond event so one of the **two visible diamond blocks** is always the disguised unlucky block;
- restores the Cobweb and Giant Slime source classes to match their actual behavior;
- clamps invalid natural-generation chance values to the safe `0-100` range;
- credits **iiLuckyDev** in both the README lineage and plugin contributor metadata.

## 📥 Download

Successful `master` builds publish the server-ready JAR directly. No `.jar.zip` extraction step is required.

Current naming format:

```text
SF_LuckyBlocks_Legacy_v1.0.1.jar
```

Download the `.jar` from the repository's **Releases** page and place it directly into the server's `plugins/` folder.

## ⚙️ Configuration

Lucky Blocks can be configured for natural generation, world restrictions, and custom surprises using vanilla items, Slimefun items, enchantments, commands, and different luck levels.

## ❤️ Credits & project lineage

- **TheBusyBiscuit** — original creator credited by the classic LuckyBlocks Slimefun project history.
- **Slimefun-Addon-Community/luckyblocks** — original community source repository in this fork chain.
- **iiLuckyDev/luckyblocks** — the **immediate upstream fork** from which `wickidcow/SF_LuckyBlocks` was created; its 1.21.11 modernization provided the modern base for this Legacy continuation.
- **LuckyBlocks and Slimefun community contributors** — maintenance, fixes, APIs, testing, and preservation across the project's lifetime.
- **wickidcow / Slimefun Legacy** — current compatibility and preservation maintenance for modern servers and albionmc.com.

This fork exists because the original addon was worth preserving. It does not claim original authorship and should not be presented as an official upstream release.

## 📜 GNU General Public License v3.0

LuckyBlocks is distributed under the **GNU General Public License v3.0 (GPLv3)**. See `LICENSE` for the complete license text.

If you distribute LuckyBlocks or a modified GPL-covered version, comply with GPLv3, including preserving applicable notices, identifying modified versions, licensing covered modified source under GPLv3, and making the required Corresponding Source available when distributing object code.

The software is supplied **without warranty** as described by GPLv3.

## ⚖️ Independence & trademark notice

**NOT AN OFFICIAL MINECRAFT PRODUCT. NOT APPROVED BY OR ASSOCIATED WITH MOJANG OR MICROSOFT.**

LuckyBlocks, Slimefun Legacy, and this maintenance fork are independent community projects. They are not sponsored, endorsed, approved, or operated by Mojang Studios or Microsoft. Minecraft-related names, brands, and assets remain the property of their respective rights holders.

This fork is also not represented as an official release of TheBusyBiscuit, Slimefun-Addon-Community, iiLuckyDev, the original Slimefun team, or any other upstream contributor unless explicitly stated by those parties.

---

<div align="center">

**🍀 Sometimes diamonds. Sometimes TNT. Always LuckyBlocks. 💥**

</div>
