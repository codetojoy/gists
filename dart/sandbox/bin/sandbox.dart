
class Foo {
  String _s;
  Foo(this._s);
  String foo() {
    return 'f.foo $_s';
  }
}

void main(List<String> arguments) {
  var foo = Foo('wolfgang');
  var map = {'abc': 3, 'def': 'mozart', 'xyz': foo};

  int i = map['abc'] as int;
  String s = map['def'] as String;
  Foo f = map['xyz'] as Foo;

  print('TRACER i: $i');
  print('TRACER s: $s');
  print('TRACER foo: ${f.foo()}');

  print('Ready.');
}
