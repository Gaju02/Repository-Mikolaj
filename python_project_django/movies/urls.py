from django.urls import path
from . import views
#url pattern to path -> to main urls in project
#if there is '', its meaning the root of website.
urlpatterns = [
    path('',views.index, name='movies_index'),
    path('<int:movie_id>', views.detail, name='movies_detail' )
]