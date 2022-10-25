package di

import data.ReportRepository
import ui.ReportingViewModel

object Injector {

    fun provideViewModel(): ReportingViewModel {
        return ReportingViewModel(provideRepository())
    }

    private fun provideRepository(): ReportRepository =
        ReportRepository()
}