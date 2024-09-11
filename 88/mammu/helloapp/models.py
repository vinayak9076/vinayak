from django.db import models
class Project(models.Model):
  topic = models.CharField(max_length=200)
languages_used = models.CharField(max_length=200)
duration = models.CharField(max_length=100)
def __str__(self):
 return self.topic
class Student(models.Model):
 name = models.CharField(max_length=100)
project = models.ForeignKey(Project, on_delete=models.CASCADE)
def __str__(self):
 return self.name

