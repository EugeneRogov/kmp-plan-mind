package com.eugenerogov.planmind.navigation

object Route {

    const val NAV_ROOT = "nav_route"

    // region screen
    const val DEFAULT = "default_screen"
    const val LOGIN = "login_screen"
    const val RECOVER_PASSWORD = "recover_password_screen"
    const val RECOVER_PASSWORD_SUCCESS = "recover_password_success_screen"
    const val NETWORK_SETTINGS = "network_settings_screen"

    const val HOME = "home_screen"
    const val TASK = "task_screen"
    const val CHECK_LIST = "checklist_screen"

    const val NOTIFICATIONS = "notifications_screen"

    const val TASK_TRACKER = "task_tracker_screen"
    const val MY_TASKS = "my_tasks_screen"
    const val ALL_TASKS = "all_tasks_screen"
    const val MY_WORK = "my_work_screen"

    const val MESSENGER = "messenger_screen"
    const val CHATS = "chats_screen"
    const val FOLDERS = "folders_screen"
    const val ROOMS = "rooms_screen"

    const val PROFILE = "profile_screen"

    const val SETTINGS = "settings_screen"
    const val ABOUT_VERSION = "about_version_screen"

    const val CODE_SCANNER = "code_scanner_screen"
    // endregion

    // region bundle
    const val TASK_BUNDLE = "task_bundle"
    const val CHECK_LIST_BUNDLE = "check_list_bundle"
    // endregion

    // region result
    const val SCAN_CODE_RESULT = "scanned_code"
    const val TASK_ARCHIVED_RESULT = "task_archived_result"
    // endregion
}
