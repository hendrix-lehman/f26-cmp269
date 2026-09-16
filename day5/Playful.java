// defines another BEHAVIOR (capability) "Can-Do"
//
// Use an Interface when you want to define a specific polymorphic contract 
// or capability that can be applied to completely unrelated classes 
// (e.g., a Dog and a Submarine can both implement Swimmable).
interface Playful {
    void playWithToy(); // abstract by default method for playing behavior
}
