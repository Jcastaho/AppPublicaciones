package com.straccion.gamermvvmapp.di

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.straccion.gamermvvmapp.core.Constants.POST
import com.straccion.gamermvvmapp.core.Constants.USERS
import com.straccion.gamermvvmapp.data.repository.AuthRepositoryImpl
import com.straccion.gamermvvmapp.data.repository.PostRepositoryImpl
import com.straccion.gamermvvmapp.data.repository.UsersRepositoryImpl
import com.straccion.gamermvvmapp.domain.repository.AuthRepository
import com.straccion.gamermvvmapp.domain.repository.PostRepository
import com.straccion.gamermvvmapp.domain.repository.UsersRepository
import com.straccion.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import com.straccion.gamermvvmapp.domain.use_cases.auth.GetCurrentUser
import com.straccion.gamermvvmapp.domain.use_cases.auth.Login
import com.straccion.gamermvvmapp.domain.use_cases.auth.Logout
import com.straccion.gamermvvmapp.domain.use_cases.auth.Signup
import com.straccion.gamermvvmapp.domain.use_cases.posts.CreatePost
import com.straccion.gamermvvmapp.domain.use_cases.posts.DeleteLikePost
import com.straccion.gamermvvmapp.domain.use_cases.posts.DeletePost
import com.straccion.gamermvvmapp.domain.use_cases.posts.GetPost
import com.straccion.gamermvvmapp.domain.use_cases.posts.GetPostByIdUser
import com.straccion.gamermvvmapp.domain.use_cases.posts.LikePost
import com.straccion.gamermvvmapp.domain.use_cases.posts.PostsUsesCases
import com.straccion.gamermvvmapp.domain.use_cases.posts.UpdatePost
import com.straccion.gamermvvmapp.domain.use_cases.users.Create
import com.straccion.gamermvvmapp.domain.use_cases.users.GetUserById
import com.straccion.gamermvvmapp.domain.use_cases.users.SaveImage
import com.straccion.gamermvvmapp.domain.use_cases.users.Update
import com.straccion.gamermvvmapp.domain.use_cases.users.UsersUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named


@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun providerFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun providerAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun providerFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    fun providesAuthUseCases(repository: AuthRepository) = AuthUseCases(
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository),
        logout = Logout(repository),
        signup = Signup(repository)
    )

    //Firestore
    @Provides
    fun providerUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

    @Provides
    fun providesFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    @Named(USERS)
    fun providesUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)

    @Provides
    @Named(USERS)
    fun providerStorageUsersRef(storage: FirebaseStorage): StorageReference = storage.reference.child(USERS)

    @Provides
    fun providesUsersUseCases(repository: UsersRepository) = UsersUseCases(
        create = Create(repository),
        getUserById = GetUserById(repository),
        update = Update(repository),
        saveImage = SaveImage(repository)
    )

    @Provides
    @Named(POST)
    fun providesPostRef(db: FirebaseFirestore): CollectionReference = db.collection(POST)

    @Provides
    @Named(POST)
    fun providerStoragePostRef(storage: FirebaseStorage): StorageReference = storage.reference.child(POST)

    @Provides
    fun providerPostRepository(impl: PostRepositoryImpl): PostRepository = impl

    @Provides
    fun providesPostsUseCases(repository: PostRepository) = PostsUsesCases(
        create = CreatePost(repository),
        getPost = GetPost(repository),
        getPostByIdUser = GetPostByIdUser(repository),
        deletePost = DeletePost(repository),
        updatePost = UpdatePost(repository),
        likePost = LikePost(repository),
        deleteLikePost = DeleteLikePost(repository)
    )
}