import SwiftUI
import Shared

struct ContentView: View {
    @State private var showContent = false
    @State private var json = ""
    var body: some View {
        VStack {
            Button("Click me!") {
                withAnimation {
                    showContent = !showContent
                }
            }

            if showContent {
                VStack(spacing: 16) {
                    Image(systemName: "swift")
                        .font(.system(size: 200))
                        .foregroundColor(.accentColor)
                    Text("SwiftUI: \(Greeting().greet())")
                }
                .transition(.move(edge: .top).combined(with: .opacity))
            }
            
            Button("execute") {
                Task {
                    do {
                        ApiExecutor().execute() { (result, error) in
                            if let result = result {
                                print(result)
                                json = result
                            }
                        }
                    }
                }
            }
            
            Text(json)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
