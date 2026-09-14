# AGENTS.md — smash-api

## What this is

`eu.smashmc:smash-api` is the **network-wide shared API** that lets all SmashMC plugins/services talk to each other
without depending on each other's implementations. It is **not** the `smash` gamemode's API, despite the name.
This module is **contracts only** — interfaces (and a few tiny dev fallbacks). Implementations live various plugins and
in `smash-core` and the backend `*-service` modules. Root package `eu.smashmc.api`. See root `AGENTS.md` / `PROJECT.md`.

## Build & test

Standalone Maven project (**no parent POM**), **Java 25**. Published to the **public** repo
`repo.smashmc.eu/public`.

```
mvn verify # compile, test, sources + javadoc jars
```

The output jar is `target/smash-api-<version>.jar` — this module does **not** use the parent's versionless
`finalName`. Javadoc has `failOnError=true`, so broken doc comments fail the build.

## How it works

- An API is normally a `public interface` (occasionally an `abstract class` when it needs a generic base, e.g.
  `Language<T>`) annotated `@SmashComponent`, declaring supported `Environment`s (`BUKKIT` / `BUNGEECORD`; default =
  any) and an optional `fallbackImpl`.
- Consumers retrieve the bound implementation via `SmashMc.getComponent(X.class)` / `getComponentOrNull(...)`.
  Registration is done by other plugins (`smash-core` scans the classpath). Prefer the static facades where they
  exist: `Lang` / `BLang`, `ProfileUtil`, `StatsHelper`, `PermissionUtil`, `SpigotAchievementsHelper`.
- Generic APIs are parameterized on the player type `<T>` (Bukkit `Player` vs Bungee `ProxiedPlayer`).
- `@Managed`, `@Debug`, `@Inject`, `@Invoke`, `@Schedule`, `@Shutdown`, `@Config`, `RegistryService`/`Registrar`
  are **declared here but executed by `smash-core`** — do not expect DI/scanning to run inside this module.
- `fallbackImpl` classes are **package-private**, live in the same package, and need a no-arg constructor. They are
  cheap no-op/sane-default stubs for dev/debug only (`SmashMc` logs a warning when one is used).

## Conventions

- Lombok, `javax.annotation` `@Nullable`/`@Nonnull`, and Guava (`Preconditions`, `Suppliers`) are available.
- Platform deps are **provided**: `paper-api` 1.8 + `bungeecord-api` — no CurrySpigot/NMS here; keep it
  platform-agnostic.
- Javadoc on public API (see above)
- Style (`.editorconfig`): **tabs**, width 4, **120 col**, **LF**, **no final newline**, single-class imports.
- Tests: JUnit Jupiter + Mockito under `src/test/java`. Isolate the registry with `SmashMc.clearComponents()` and
  `Environment.setEnvironmentUnchecked(...)`.
- Keep this module contract-only: no gamemode logic, no listeners, no I/O.
