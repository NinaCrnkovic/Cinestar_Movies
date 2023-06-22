------Procedure----


GO

USE CineStarMovies;
GO


--------------------------Movie------------
----- CREATE PROCEDURE
CREATE PROCEDURE CreateMovie
    @Title NVARCHAR(255),
    @OriginalTitle NVARCHAR(255),
    @Description NVARCHAR(MAX),
    @Duration INT,
    @Year INT,
    @Poster NVARCHAR(MAX),
    @Rating NVARCHAR(50),
    @Link NVARCHAR(MAX),
    @Guid NVARCHAR(MAX),
    @Reservation NVARCHAR(MAX),
    @DisplayDate DATETIME,
    @Performances NVARCHAR(MAX),
    @Sort INT,
    @Trailer NVARCHAR(255),
    @InsertedID INT OUTPUT
AS
BEGIN
    IF NOT EXISTS (SELECT * FROM Movie WHERE Title = @Title AND Year = @Year)
    BEGIN
        INSERT INTO Movie(Title, OriginalTitle, Description, Duration, Year, Poster, Rating, Link, Guid, Reservation, DisplayDate, Performances, Sort, Trailer)
        VALUES (@Title, @OriginalTitle, @Description, @Duration, @Year, @Poster, @Rating, @Link, @Guid, @Reservation, @DisplayDate, @Performances, @Sort, @Trailer)
        
        SET @InsertedID = SCOPE_IDENTITY()
    END
    ELSE
    BEGIN
        SELECT @InsertedID = ID FROM Movie WHERE Title = @Title AND Year = @Year
    END
END
GO


-- UPDATE PROCEDURE
CREATE PROCEDURE UpdateMovie
    @ID INT,
    @Title NVARCHAR(255),
    @OriginalTitle NVARCHAR(255),
    @Description NVARCHAR(MAX),
    @Duration INT,
    @Year INT,
    @Poster NVARCHAR(MAX),
    @Rating NVARCHAR(50),
    @Link NVARCHAR(MAX),
    @Guid NVARCHAR(MAX),
    @Reservation NVARCHAR(MAX),
    @DisplayDate DATETIME,
    @Performances NVARCHAR(MAX),
    @Sort INT,
    @Trailer NVARCHAR(255)
AS
BEGIN
    UPDATE Movie
    SET Title = @Title,
        OriginalTitle = @OriginalTitle,
        Description = @Description,
        Duration = @Duration,
        Year = @Year,
        Poster = @Poster,
        Rating = @Rating,
        Link = @Link,
        Guid = @Guid,
        Reservation = @Reservation,
        DisplayDate = @DisplayDate,
        Performances = @Performances,
        Sort = @Sort,
        Trailer = @Trailer
    WHERE ID = @ID
END
GO

-- DELETE PROCEDURE
CREATE PROCEDURE DeleteMovie
    @ID INT
AS
BEGIN
    DELETE FROM Movie
    WHERE ID = @ID
END
GO

-- SELECT PROCEDURE
CREATE PROCEDURE SelectMovie
    @ID INT
AS
BEGIN
    SELECT * FROM Movie
    WHERE ID = @ID
END
GO

CREATE PROCEDURE SelectMovies
AS
BEGIN
    SELECT * FROM Movie
END
GO

------------------------ Genre
----- CREATE PROCEDURE

CREATE PROCEDURE CreateGenre
    @Name NVARCHAR(255),
    @InsertedID INT OUTPUT
AS
BEGIN
    IF NOT EXISTS (SELECT * FROM Genre WHERE Name = @Name)
    BEGIN
        INSERT INTO Genre(Name)
        VALUES (@Name)

        SET @InsertedID = SCOPE_IDENTITY()
    END
    ELSE
    BEGIN
        SELECT @InsertedID = ID FROM Genre WHERE Name = @Name
    END
END
GO

----- UPDATE PROCEDURE

CREATE PROCEDURE UpdateGenre
    @ID INT,
    @Name NVARCHAR(255)
AS
BEGIN
    UPDATE Genre
    SET Name = @Name
    WHERE ID = @ID
END
GO

----- DELETE PROCEDURE
CREATE PROCEDURE DeleteGenre
    @ID INT
AS
BEGIN
    DELETE FROM Genre WHERE ID = @ID
END
GO

----- SELECT PROCEDURE
CREATE PROCEDURE SelectGenre
    @ID INT
AS
BEGIN
    SELECT * FROM Genre WHERE ID = @ID
END
GO

CREATE PROCEDURE SelectGenres
AS
BEGIN
    SELECT * FROM Genre
END
GO



----------------------------- Person
----- CREATE PROCEDURE
CREATE PROCEDURE CreatePerson
    @Name NVARCHAR(255),
    @InsertedID INT OUTPUT
AS
BEGIN
    IF NOT EXISTS (SELECT * FROM Person WHERE Name = @Name)
    BEGIN
        INSERT INTO Person(Name)
        VALUES (@Name)

        SET @InsertedID = SCOPE_IDENTITY()
    END
    ELSE
    BEGIN
        SELECT @InsertedID = ID FROM Person WHERE Name = @Name
    END
END
GO

----- UPDATE PROCEDURE
CREATE PROCEDURE UpdatePerson
    @ID INT,
    @Name NVARCHAR(255)
AS
BEGIN
    UPDATE Person
    SET Name = @Name
    WHERE ID = @ID
END
GO
----- DELETE PROCEDURE
CREATE PROCEDURE DeletePerson
    @ID INT
AS
BEGIN
    DELETE FROM Person WHERE ID = @ID
END
GO
----- SELECT PROCEDURE
CREATE PROCEDURE SelectPerson
    @ID INT
AS
BEGIN
    SELECT * FROM Person WHERE ID = @ID
END
GO

CREATE PROCEDURE SelectPersons
AS
BEGIN
    SELECT * FROM Person
END
GO

---------------------- Role
----- CREATE PROCEDURE
CREATE PROCEDURE CreateRole
    @Role NVARCHAR(255),
    @InsertedID INT OUTPUT
AS
BEGIN
    IF NOT EXISTS (SELECT * FROM Role WHERE Role = @Role)
    BEGIN
        INSERT INTO Role(Role)
        VALUES (@Role)

        SET @InsertedID = SCOPE_IDENTITY()
    END
    ELSE
    BEGIN
        SELECT @InsertedID = ID FROM Role WHERE Role = @Role
    END
END
GO

----- UPDATE PROCEDURE
CREATE PROCEDURE UpdateRole
    @ID INT,
    @Role NVARCHAR(255)
AS
BEGIN
    UPDATE Role
    SET Role = @Role
    WHERE ID = @ID
END
GO
----- DELETE PROCEDURE
CREATE PROCEDURE DeleteRole
    @ID INT
AS
BEGIN
    DELETE FROM Role WHERE ID = @ID
END
GO
----- SELECT PROCEDURE
CREATE PROCEDURE SelectRole
    @ID INT
AS
BEGIN
    SELECT * FROM Role WHERE ID = @ID
END
GO

CREATE PROCEDURE SelectRoles
AS
BEGIN
    SELECT * FROM Role
END
GO


----------------------------- MovieGenre
----- CREATE PROCEDURE

CREATE PROCEDURE CreateMovieGenre
    @MovieID INT,
    @GenreID INT
AS
BEGIN
    IF NOT EXISTS (SELECT * FROM MovieGenre WHERE MovieID = @MovieID AND GenreID = @GenreID)
    BEGIN
        INSERT INTO MovieGenre(MovieID, GenreID)
        VALUES(@MovieID, @GenreID)
    END
END
GO

CREATE PROCEDURE UpdateMovieGenre
    @OldMovieID INT,
    @OldGenreID INT,
    @NewMovieID INT,
    @NewGenreID INT
AS
BEGIN
    UPDATE MovieGenre
    SET MovieID = @NewMovieID, GenreID = @NewGenreID
    WHERE MovieID = @OldMovieID AND GenreID = @OldGenreID
END
GO


CREATE PROCEDURE DeleteMovieGenre
    @MovieID INT,
    @GenreID INT
AS
BEGIN
    DELETE FROM MovieGenre WHERE MovieID = @MovieID AND GenreID = @GenreID
END
GO

CREATE PROCEDURE SelectMovieGenres
AS
BEGIN
    SELECT * FROM MovieGenre
END
GO

-------------------------- MoviePersonRole
CREATE PROCEDURE CreateMoviePersonRole
    @MovieID INT,
    @PersonID INT,
    @RoleID INT
AS
BEGIN
    IF NOT EXISTS (SELECT * FROM MoviePersonRole WHERE MovieID = @MovieID AND PersonID = @PersonID AND RoleID = @RoleID)
    BEGIN
        INSERT INTO MoviePersonRole(MovieID, PersonID, RoleID)
        VALUES(@MovieID, @PersonID, @RoleID)
    END
END
GO
----- DELETE PROCEDURE

CREATE PROCEDURE DeleteMoviePersonRole
    @MovieID INT,
    @PersonID INT,
    @RoleID INT
AS
BEGIN
    DELETE FROM MoviePersonRole WHERE MovieID = @MovieID AND PersonID = @PersonID AND RoleID = @RoleID
END
GO
----- UPDATE PROCEDURE
CREATE PROCEDURE UpdateMoviePersonRole
    @OldMovieID INT,
    @OldPersonID INT,
    @OldRoleID INT,
    @NewMovieID INT,
    @NewPersonID INT,
    @NewRoleID INT
AS
BEGIN
    UPDATE MoviePersonRole
    SET MovieID = @NewMovieID, PersonID = @NewPersonID, RoleID = @NewRoleID
    WHERE MovieID = @OldMovieID AND PersonID = @OldPersonID AND RoleID = @OldRoleID
END
GO
----- SELECT PROCEDURE
CREATE PROCEDURE SelectMoviePersonsRoles
    @MovieID INT
AS
BEGIN
    SELECT p.Name as Person, r.Role as Role
    FROM Person p
    JOIN MoviePersonRole mpr ON p.ID = mpr.PersonID
    JOIN Role r ON mpr.RoleID = r.ID
    WHERE mpr.MovieID = @MovieID
END
GO


CREATE PROCEDURE SelectMoviePersonRoles
AS
BEGIN
    SELECT * FROM MoviePersonRole
END
GO


-- AccountType
CREATE PROCEDURE CreateAccountType
    @Type NVARCHAR(255),
    @ID INT OUTPUT
AS
BEGIN
    IF NOT EXISTS (SELECT * FROM AccountType WHERE Type = @Type)
    BEGIN
        INSERT INTO AccountType(Type)
        VALUES (@Type)

        SET @ID = SCOPE_IDENTITY()
    END
    ELSE
    BEGIN
        SELECT @ID = ID FROM AccountType WHERE Type = @Type
    END
END
GO

CREATE PROCEDURE UpdateAccountType
    @ID INT,
    @NewType NVARCHAR(255)
AS
BEGIN
    UPDATE AccountType
    SET Type = @NewType
    WHERE ID = @ID
END
GO

CREATE PROCEDURE SelectAccountType
    @ID INT
AS
BEGIN
    SELECT * FROM AccountType
    WHERE ID = @ID
END
GO

CREATE PROCEDURE DeleteAccountType
    @ID INT
AS
BEGIN
    DELETE FROM AccountType
    WHERE ID = @ID
END
GO

CREATE PROCEDURE SelectAccountTypes
AS
BEGIN
    SELECT * FROM AccountType
END
GO

-- User
CREATE PROCEDURE CreateUser
    @Username NVARCHAR(255),
    @PwdHash NVARCHAR(MAX),
    @PwdSalt NVARCHAR(MAX),
    @AccountTypeID INT,
    @ID INT OUTPUT
AS
BEGIN
    IF NOT EXISTS (SELECT * FROM [User] WHERE Username = @Username)
    BEGIN
        INSERT INTO [User](Username, PwdHash, PwdSalt, AccountTypeID)
        VALUES (@Username, @PwdHash, @PwdSalt, @AccountTypeID)

        SET @ID = SCOPE_IDENTITY()
    END
    ELSE
    BEGIN
        SELECT @ID = ID FROM [User] WHERE Username = @Username
    END
END
GO

CREATE PROCEDURE UpdateUser
    @ID INT,
    @NewUsername NVARCHAR(255),
    @NewPwdHash NVARCHAR(MAX),
    @NewPwdSalt NVARCHAR(MAX),
    @NewAccountTypeID INT
AS
BEGIN
    UPDATE [User]
    SET Username = @NewUsername, PwdHash = @NewPwdHash, PwdSalt = @NewPwdSalt, AccountTypeID = @NewAccountTypeID
    WHERE ID = @ID
END
GO

CREATE PROCEDURE SelectUser
    @ID INT
AS
BEGIN
    SELECT * FROM [User]
    WHERE ID = @ID
END
GO

CREATE PROCEDURE DeleteUser
    @ID INT
AS
BEGIN
    DELETE FROM [User]
    WHERE ID = @ID
END
GO

CREATE PROCEDURE SelectUsers
AS
BEGIN
    SELECT * FROM [User]
END
GO

-- FavoriteMovie
CREATE PROCEDURE CreateFavoriteMovie
    @UserID INT,
    @MovieID INT
AS
BEGIN
    IF NOT EXISTS (SELECT * FROM FavoriteMovie WHERE UserID = @UserID AND MovieID = @MovieID)
    BEGIN
        INSERT INTO FavoriteMovie(UserID, MovieID)
        VALUES (@UserID, @MovieID)
    END
END
GO

CREATE PROCEDURE DeleteFavoriteMovie
    @UserID INT,
    @MovieID INT
AS
BEGIN
    DELETE FROM FavoriteMovie
    WHERE UserID = @UserID AND MovieID = @MovieID
END
GO

CREATE PROCEDURE SelectFavoriteMovies
    @UserID INT
AS
BEGIN
    SELECT m.*
    FROM Movie m
    JOIN FavoriteMovie fm ON m.ID = fm.MovieID
    WHERE fm.UserID = @UserID
END
GO
