package com.eugenerogov.planmind.data.remote

object Endpoint {
    const val AUTH_TOKEN = "hub/connect/token"

    const val PROFILE_GENERAL_INFO = "pf/api/profile/general-info"

    const val TASKS_LIST = "pm/api/task/list"
    const val TASK = "pm/api/task/{id}"
    const val TASK_LAYOUT = "pm/api/task-layout/{id}"
    const val TASK_UPDATE_STATE = "/pm/api/task/bulk-update-state"
    const val TASK_DELETE = "/pm/api/task"
    const val TASK_ARCHIVE = "pm/api/task/archive/{id}"
    const val TASK_UNARCHIVE = "pm/api/task/unarchive/{id}"
    const val TASK_BACK_TO_AUTHOR = "pm/api/task/back-to-author/{id}"

    const val PROCESS = "bpm/api/run-process/step/{id}"
    const val PROCESS_STEP_ID = "pm/api/bpm/get-process-step-id/{id}"
    const val PROCESS_DOWNLOAD_DOC = "/ds/api/electronic-documents/print/"

    const val CHECK_LISTS = "conformityassessment/api/checklists"
    const val CHECK_LIST = "conformityassessment/api/checklists/{id}"
    const val CHECK_LIST_BULK_ANSWER = "conformityassessment/api/checklists/bulk-answer"

    const val FACT_WORK = "pm/api/fact-work"
    const val TASK_COMMENTS = "pm/api/task/comments"
}
