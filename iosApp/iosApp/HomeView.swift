import SwiftUI
import Shared

struct HomeView : View {
    var body: some View {
        Text(StringsHelper.shared.get(resource: Res.string.shared.app_name))
    }
}
