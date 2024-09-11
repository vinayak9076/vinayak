from django.urls import path
from .views import StudentListView, StudentDetailView, CourseDetailView

urlpatterns = [
    path('', StudentListView.as_view(), name='student-list'),
    path('student/<int:pk>/', StudentDetailView.as_view(), name='student-detail'),
    path('course/<int:pk>/', CourseDetailView.as_view(), name='course-detail'),
]
