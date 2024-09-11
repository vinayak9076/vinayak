from django.contrib import admin
from .models import Student,Course
admin.site.register(Student)
class StudentAdmin(admin.ModelAdmin):
    list_display=['first_name','last_name','email']
    admin.site.register(Course)
class CourseAdmin(admin.ModelAdmin):
    list_display=['name','description']


# Register your models here.
