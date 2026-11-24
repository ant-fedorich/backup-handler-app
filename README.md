# Backup Handler

## Build and run
Open the project in Android Studio and run the `app` configuration, or run `./gradlew :app:installDebug`.

Tested on the Android Emulator (Medium Phone, Android 11, API 30).

A file with `fail_` in its name fails on purpose, to demo a failure.

## Time spent
3:15

## Unfinished
- the failure reason is saved but not shown in the UI
- graceful uuid and prefix for backup files
- consider parallel copying with a limit

## AI assistance
I used AI step by step: to discuss the requirements and edge cases, generate small parts of the code that I reviewed and fixed. Used some skills for generating boilerplate code.
Out of scope for AI: vibe-coding, spec-driven development (SDD) with a framework.