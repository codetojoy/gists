
* AUG 2020
* This works on my machine elsewhere, but won't work in this folder.
* I've tried clean/re-build etc for a long time. I don't know what's wrong.

* adding this to `pkg/sandbox_bg.js` seems to work:

```
import { update_message } from '../update.js';
```

* which may mean that it is an issue with `#wasm_bindgen` or webpack, or more likely,
  my usage of them
* many braincells have died here 
