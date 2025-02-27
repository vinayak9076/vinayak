from django.views.generic import ListView, DetailView
from .models import Student, Course

class StudentListView(ListView):
    model = Student
    template_name = 'courses/student_list.html'
    context_object_name = 'students'

class StudentDetailView(DetailView):
    model = Student
    template_name = 'courses/student_detail.html'
    context_object_name = 'student'

class CourseDetailView(DetailView):
    model = Course
    template_name = 'courses/course_detail.html'
    context_object_name = 'course'

