# 🍀 LuckyBlocks — Slimefun Legacy

<p align="center">
  <b>Break a block. Roll the dice. Hope the server gods are feeling generous.</b><br>
  🟨 ➜ ❓ ➜ 💎 / 🐔 / 💥 / ☠️
</p>

LuckyBlocks adds random-event blocks to **Slimefun Legacy**. Crack one open and you might get treasure, enchanted gear, mobs, potions, explosions, falling anvils, chaos... or something much worse.

This repository is an **unofficial preservation and compatibility fork** of the original LuckyBlocks (Slimefun Edition), maintained for modern Minecraft servers while keeping the classic addon feel and original Slimefun item IDs.

## 🎲 The blocks

| Block | Luck | What could possibly happen? |
| --- | ---: | --- |
| 🟨 **Lucky Block** | `0` | The full good/neutral/bad surprise pool. Pure gambling with blocks. |
| ✨ **Very Lucky Block** | `+80` | Tilts the odds toward shiny things and happy accidents. |
| ☠️ **Very Unlucky Block** | `-80` | You knew what you were doing when you crafted this. |
| 📦 **Pandora's Box** | `ERROR` | Special Pandora surprises. Open responsibly. Or don't. |

## 💥 What does it do?

LuckyBlocks keeps the original addon gameplay, including dozens of random surprises such as:

- 💎 diamonds, emeralds, valuables and resource drops
- ⚔️ wildly enchanted lucky weapons, tools and armor
- 🧪 lucky and unlucky potion showers
- 🐺 tamed animals and strange mob encounters
- 🐔 chicken rain, rainbow sheep and other nonsense
- 🧟 zombies, witches, creepers and giant slimes
- 🧨 TNT, explosions and airborne hazards
- ⚒️ falling anvils and environmental traps
- 🕳️ holes, cobwebs, water traps and other unpleasant surprises
- 📦 unique **Pandora's Box** outcomes
- 🛠️ configurable custom surprises, items and commands
- 🌍 optional naturally generated Lucky Blocks in newly populated chunks

No guarantee is made regarding the continued existence of your roof after opening Pandora's Box.

## 🧪 Compatibility

This Legacy fork is maintained for the modern Albion-style Slimefun stack:

- **Minecraft:** 1.21.11+
- **Paper:** 26.2+
- **Purpur:** 26.2+ / Paper-compatible builds
- **Folia:** 26.2+ with explicit `folia-supported: true`
- **Build JDK:** Java 25
- **Plugin bytecode:** Java 21
- **Slimefun:** **Slimefun Legacy** primary target

The GitHub workflow performs a dedicated **Folia API compile check** and then builds the release JAR against **Paper 26.2**. Purpur support follows its Paper API compatibility.

## 📦 Downloads

Every successful `master` build produces a directly usable server JAR:

```text
SF_LuckyBlocks_Legacy_v1.0.0.jar
```

The Actions artifact is uploaded as the **raw `.jar` file**, not a `.jar.zip`, and the same JAR is attached directly to the GitHub release.

Drop it straight into:

```text
/plugins/
```

No extracting. No hunting through archives. Just Lucky Blocks. 🍀

## ⚙️ Configuration

You can control natural Lucky Block generation and create your own custom surprises in `config.yml`.

```yaml
chance: 2

world-blacklist:
  - world_nether
  - world_the_end
```

Custom surprises can contain normal Minecraft items, Slimefun items, enchantments and commands.

## 🏛️ Preservation & credits

**LuckyBlocks (Slimefun Edition) was originally created by TheBusyBiscuit and the Slimefun community.** The concepts, original gameplay, item IDs and project history belong to that work.

This fork exists to keep the addon alive on current Minecraft versions and integrate it cleanly with **Slimefun Legacy**, not to erase or replace the original authorship.

Original community project:

- `Slimefun-Addon-Community/luckyblocks`

Modern 1.21.11 work used as the starting point:

- `iiLuckyDev/luckyblocks`

Additional modern API/Folia fixes from other community forks may be incorporated where they preserve the original English addon behavior.

## 📜 License

LuckyBlocks remains open source under the **GNU General Public License v3.0**, matching the `LICENSE` included in this repository.

---

<p align="center">
  🍀 <b>Good luck.</b> 🍀<br>
  <sub>You're probably going to need it.</sub>
</p>
