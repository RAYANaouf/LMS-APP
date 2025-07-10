package com.jethings.study.presentation.view.screens.TrainingProgram.event




sealed class CourseEvent {

    class RequestCourse( val courseId : Long) : CourseEvent()

}