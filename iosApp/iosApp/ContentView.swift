import SwiftUI
import MultiPlatformLibrary
import Combine

struct ContentView: View {
    @State var isAuthorized: Bool = true
    
    var body: some View {
        if isAuthorized {
            MainView {
                withAnimation(.linear) {
                    isAuthorized = false
                }
            }
        } else {
            LoginView {
                withAnimation(.easeIn) {
                    isAuthorized = true
                }
            }
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}

struct MainView: View {
    let onUnauthorized: () -> Void
    
    var body: some View {
        NavigationView {
            BookListView()
                .toolbar {
                    ToolbarItem(placement: .navigationBarTrailing) {
                        Button("Logout") {
                            self.onUnauthorized()
                        }
                    }
                }
        }
    }
}
