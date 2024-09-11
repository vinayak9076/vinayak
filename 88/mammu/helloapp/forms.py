from django import forms
from .models import Student, Project
class StudentForm(forms.ModelForm):
 project_topic = forms.CharField(max_length=200, label="Project Topic")
 project_languages_used = forms.CharField(max_length=200, label="Languages Used")
 project_duration = forms.CharField(max_length=100, label="Duration")
 class Meta:
   model = Student
   fields = ['name']
   def save(self, commit=True):
     Student = super().save(commit=False)
     project, created = Project.objects.get_or_create(
       topic=self.cleaned_data['project_topic'], 
       languages_used=self.cleaned_data['project_languages_used'], 
       duration=self.cleaned_data['project_duration']
    )
     Student.project = project
     if commit:
       Student.save()
       return Student