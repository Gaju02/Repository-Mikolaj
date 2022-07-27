from django.http import HttpResponse, Http404
from django.shortcuts import get_object_or_404, render, get_list_or_404
from .models import Movie

#main page (index) to url
def index(request):
    movies = Movie.objects.all()
    return render(request,'index.html',{ 'movies':movies})


def detail(request,movie_id): 
        movie=get_object_or_404(Movie, pk=movie_id)
        return render(request,'detail.html',{'movie':movie})