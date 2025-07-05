package com.eugenerogov.planmind.navigation

sealed class Screen(val route: String) {

    data object Default : Screen(Route.DEFAULT)
    data object Login : Screen(Route.LOGIN)
    data object RecoverPassword : Screen(Route.RECOVER_PASSWORD)
    data object RecoverPasswordSuccess : Screen(Route.RECOVER_PASSWORD_SUCCESS)
    data object NetworkSettings : Screen(Route.NETWORK_SETTINGS)

    data object Home : Screen(Route.HOME)
    data object Task : Screen(Route.TASK + "?" + Route.TASK_BUNDLE + "={" + Route.TASK_BUNDLE + "}")
    data object CheckList : Screen(
        Route.CHECK_LIST + "?" + Route.CHECK_LIST_BUNDLE + "={" + Route.CHECK_LIST_BUNDLE + "}"
    )
    data object Notifications : Screen(Route.NOTIFICATIONS)

    data object TaskTracker : Screen(Route.TASK_TRACKER)
    data object MyTasks : Screen(Route.MY_TASKS)
    data object AllTasks : Screen(Route.ALL_TASKS)
    data object MyWork : Screen(Route.MY_WORK)

    data object Messenger : Screen(Route.MESSENGER)
    data object Chats : Screen(Route.CHATS)
    data object Folders : Screen(Route.FOLDERS)
    data object Rooms : Screen(Route.ROOMS)

    data object Profile : Screen(Route.PROFILE)

    data object Settings : Screen(Route.SETTINGS)
    data object AboutVersion : Screen(Route.ABOUT_VERSION)

    data object CodeScanner : Screen(Route.CODE_SCANNER)
}
