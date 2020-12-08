package effectivejava.chapter5.item30.example;

// Lev 1
class Foot {}

// Lev 2
class Fruit extends Foot{}
class Meat extends Foot{}

// Lev 3
class Apple extends Fruit {}
class Banana extends Fruit {}
class Pork extends Meat {}
class Beef extends Meat {}

// Lev 4
class RedApple extends Apple {}
class GreenApple extends Apple {}