def call(String name = 'Test') {
    // Any valid steps can be called from this code, just like in other
    // Scripted Pipeline
    echo "It Works, ${name}."
    sh "test"
}

return this