from django.urls import path
from .views import create_student, student_list, success # type: ignore

urlpatterns = [
path('create_student/', create_student, name='create_student'),
 path('student_list/', student_list, name='student_list'),
   path('success/', success, name='success'),
]