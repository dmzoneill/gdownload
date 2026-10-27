# gdownload

A Java Swing download manager for Google Video links, plus a small Java
timer example.

## Contents

- `GoogleDownload/Gdownload`: a download manager that takes a Google
  Video link, finds the video stream URL from its `docid` and downloads
  it. Downloads are listed in a table with filename, progress and
  status, and can be paused, resumed, cancelled and cleared.
- `javaTimerCrossthreading`: a Swing example that runs three timers on
  separate threads and updates the UI from them.

## Tech

- Java with Swing

## Running

```sh
cd GoogleDownload
javac Gdownload/*.java
java Gdownload.DownloadManager
```

Google Video has since closed, so the downloader no longer works against
the live service.

## License

Licensed under the Apache License 2.0. See [LICENSE](LICENSE).
