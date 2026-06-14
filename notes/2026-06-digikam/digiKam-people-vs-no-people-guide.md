# Sorting Photos into "People" vs "No People" with digiKam (Windows 10)

## The one key idea

Use **Face Detection**, *not* Face Recognition.

- **Detection** = "is there a face in this photo? yes / no" — this is all you need.
- **Recognition** = "*whose* face is this?" — requires tedious manual training (tagging 5+ examples of each person). **Skip it entirely.**

Analogy: detection is a metal detector that beeps when it finds metal; recognition tries to identify the exact coin. You only need the beep.

## At a glance (the flow)

    Install digiKam  ->  Point it at the photo folders  ->  Let face models download
          |
          v
    Run "Scan Collection for Faces"  ->  choose DETECT ONLY  ->  let it run (can take hours)
          |
          v
    All photos with a face land in the "People" view (as "Unknown")
          |
          v
    Tag that whole group "People"  ->  filter by tag = people group / no tag = nature group
          |
          v
    Move each group into folders, year by year  ->  eyeball-check the edges

---

## Before you start

1. **Download digiKam** (free, open-source) from the official site: https://www.digikam.org/download/
   - Pick the **Windows 64-bit installer**. digiKam runs fine on Windows 10.
2. **Be online for the first launch.** The first time face detection runs, digiKam downloads its deep-learning face models from the web. After that it works fully offline — none of the photos ever leave the PC.
3. **Back up first.** Before bulk-moving anything, copy the photo folders to an external drive or second location. digiKam itself is non-destructive, but you'll be moving files at the end, so a safety copy is wise.

---

## Step-by-step

### 1. Set up the photo library
- Launch digiKam. On first run it asks where your photos live — point it at the top folder that holds your year-by-year folders.
- It will catalog everything (this builds a searchable index; the originals stay where they are).

### 2. Run face detection
- In the **left sidebar**, click the **People** tab (the person icon).
- Click **Scan Collection for Faces**.
- In the options, choose the scan type **"Detect faces"** (detection only — do **not** pick "recognize").
- Optionally limit the scan to specific albums/years if you'd rather do it in batches.
- Start the scan. A progress indicator shows in the bottom-right.

> **Time note:** This is the slow part. A few hundred photos is quick; many thousands can take a few hours. It's fine to leave it running overnight.

### 3. See the results
- When it finishes, the **People** view shows every photo where a face was found, grouped under **Unknown**.
- **These are your "people" photos.** Everything digiKam did *not* tag is your "nature / no-people" group.

---

## Splitting into two groups (the practical method)

digiKam labels detected faces but doesn't auto-sort files into folders, so here's a clean way to separate them:

1. In the **People > Unknown** view, select **all** the detected-face photos (Ctrl+A).
2. Apply a single custom tag to the whole selection — e.g. create and assign a tag called **`People`**.
3. Now use the **Tag Filter** panel (right side):
   - Filter **by the `People` tag** -> shows your *people* group.
   - Filter for items **without** that tag (the filter has a "without selected tags" / inverse option) -> shows your *no-people* group.
4. With a group displayed, select all and **move them into a destination folder** (you can keep the year structure, e.g. `2019/People` and `2019/Nature`).

---

## Sanity check (don't skip this)

Detection finds **faces**, which is *almost* the same as "people," but not perfectly:

- A person photographed **from behind**, or very **small/distant**, may be missed -> lands in the "no-people" pile by mistake.
- Occasionally a **face-like pattern** (a statue, a poster, clouds) triggers a false positive.

For family photos these are rare edge cases. A quick visual skim of each finished group catches them.

---

## Quick recap
- Free, runs locally, nothing uploaded.
- **Detection only** — ignore recognition/naming.
- People = whatever digiKam tagged with a face; everything else = no people.
- Back up before moving files; eyeball the results at the end.
