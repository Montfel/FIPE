import SwiftUI

struct NavigationGraph: View {
    @State var path = NavigationPath()
    
    var body: some View {
        NavigationStack(path: $path) {
            HomeView()
            .navigationDestination(for: Screen.self) { screen in
                switch screen {
                case .home: HomeView()
                case.search: SearchView()
                }
            }
        }
    }
}
