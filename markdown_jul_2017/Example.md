a[href="#spoiler"] {
  text-decoration: none !important;
  cursor: default;
  margin-bottom: 10px;
  padding: 10px;
  background-color: #FFF8DC;
  border-left: 2px solid #ffeb8e;
  display: inline-block;
}
a[href="#spoiler"]::after {
  content: attr(title);
  color: #FFF8DC;
  padding: 0 0.5em;
}
a[href="#spoiler"]:hover::after,
a[href="#spoiler"]:active::after {
  cursor: auto;
  color: black;
  transition: color .5s ease-in-out;
}

### Show/Hide Example

Q: How many guitarists does it take to change a lightbulb?

<p>
  <a href="#spoiler" title="Spoiler Filled Text"></a>
</p>
