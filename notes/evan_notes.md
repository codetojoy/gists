
## item 1

I was going to mention you didn't have a `requirements.txt`, but you appear to be using Pipenv. Wahoo!

As a convenience for those who don't have Pipenv, you can do the following:

```bash
pipenv run pip freeze > requirements.txt
```

And those Pipenvless users could run

```bash
pip install -f requirements.txt
```

Or, you could leave those dinosaurs in the dust.

### item 1

* when I ran the first command, the resulting `requirements.txt` is empty 

---

## item 2

https://github.com/codetojoy/WarO_Python/blob/master/README.pipenv.md

Your note:

> Presumably, in this env, pytest is already installed. (I'm not sure how to test this exactly.)

Just type in `which pytest` in your terminal to figure out where pytest is coming from. It'll be under `~` (home) somewhere under a dot directory (directory starting with a dot for it's name) if it's coming from the Pipenv-created virtual environment.

And once you are in `pipenv shell`, the pytest you run will be the one installed with the pipenv environment, even if you installed pytest in your global Python. Running `pipenv shell` temporarily changes your `$PATH`.

---

## item 3

https://github.com/codetojoy/WarO_Python/blob/master/README.docker.md

Any reason `docker pull python:3.8.3` needs to be part of the instructions? I thought the docker command in `run_docker.sh` will pull it down if its not already local. And if not, any reason why the `docker pull` can be in `docker_run.sh`?

---

## item 4

https://github.com/codetojoy/WarO_Python/blob/master/README.docker.interactive.md

This is some hard-core docker. Every vowel needs some umlauts.

I find creating a `Dockerfile` much easier. Let the machine do the work.

```Dockerfile
FROM python:3.6

RUN pip install pytest

ENV PYTHONPATH /app/waro

ADD waro /app/waro
ADD tests /app/tests
ADD config.json /app/config.json
ADD config.interactive.json /app/config.interactive.json
ADD config.non.interactive.json /app/config.non.interactive.json

WORKDIR /app

ENTRYPOINT [ "python" ]
```

(There are much better dockerfiles)

Then do the following:

```bash
docker build -t waro . # build image with Waro in it, and tag it `waro`
docker run waro waro/main.py config.json # Run continer based on `waro` image (in the container, this translates to `python waro/main.py config.json`)
docker run waro -m pytest # run the tests (in the container, this translates to `python -m pytest`)
```

Basically, running `docker run waro` will run a container and immediately execute "python" (as per ENTRYPOINT in the Dockerfile)

If need be, you can mount extra config files into the container to run other war-o strategies.

---

## item 5

You are not closing your config file. But remembering to close files is for suckers. Use a "with" which will autoclose, even if an exception is encountered.

Don't worry, the return statements being in the with block interfere with auto close.

```python
def build_config_from_json_file(json_file_path):
    """ Build a configuration from a JSON file. """
    try:
        with open(json_file_path, "r") as json_file:
            json_str = json_file.read()
            return build_config_from_json(json_str)
    except:
        e = sys.exc_info()[0]
        print("illegal json: " + str(e))
        sys.exit(-1)
```

Also, the named tuple you will want to define at the top level of the module so editors and optional python typing can find it. It works perfectly fine where it is, but it is unusual to see it scoped to a function. Think of named tuples as a

And as for exception handling, I see you've read the Python wiki. I understand the need to catch absolutely everything. But it's the Python equivalent of Java's `catch (Exception e)`.

```python
    except (PermissionError, FileNotFoundError) as e:
        print(e)
        sys.exit(-1)
```
