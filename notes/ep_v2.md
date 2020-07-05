# dealer.py Notes

In dealer, the line `hands = self.partition(deck, self.config.num_cards_per_hand)` can be replaced by a list comprehension. Right now it calls a methond that converts a generator to a list.

In Python there are two things which are closely related:

- List Comprehensions
- Generators

List Comprehensions are used to build a list, and will build the entire list imediately. It's faster, but uses more memory.

Generators generates the part of the list you're currently iterating over (the element). It can be used to iterate over a large number of items without having all the items in memory. It uses next to no memory, but is much slower, since it has to proecess something (i.e. build the element) then yield the result.

https://stackoverflow.com/questions/47789/generator-expressions-vs-list-comprehension

What you're doing in dealer.py, is using a generator then calling list to turn it into a list anyway. You need the whole result in memory because you're going to use it later. And to keep it readable, you are drilling down into two separate methods which don't appear to be reused.

The list comprehension would look similar to `[deck[i:i+hand_size] for i in range(0, len(deck), hand_size)]`

I would probably make `partition` return the result of a list comprehension, as 'partition' is a good name and gives a 'why' better than a simple comment would. But maybe prefix is with an underscore to flag it as "for private use". Even though Python has no concept of private, an underscore at the star of a name is a convention that says "I don't intend for this to be used outside the context of this class, so use at your own peril. It gives you permission to change the implementation later, or inline the logic somewhere else.

Another note about partition_generator is one f the paramteres is "list". It will shadow the `list` construtor function. It's basically hidng the builtin Python function by covering it up with some data that's not callable (once you leave the context of the method, `list` is back to normal, but it can cause confusion).

# hand.py notes

For the `__str__` in `Hand`, you could use the python ternary to make it look smaller.

People hate it, but if used sparingly, it makes things easier to read.

```python
    def __str__(self):
        return f"cards: {self.cards}" if self.cards else ""
```

f-strings are available in Python 3.6 and up. They are close to Groovy strings, just without the `$`.

Also, empty list or the value `None` are falsy in Python, so if you want to do something if there are things in the list, the Pythonic way is to test the list for truthiness.

# player.py notes

Are the `build_player_stats` and `build_bid` necessary any more? They don't appear to give you any benefit other than hiding that you're creating a new tuple. This is super-subjective.
