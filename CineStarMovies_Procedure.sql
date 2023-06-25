------Procedure----


GO

USE CineStarMovies;
GO


--------------------------Movie------------
----- CREATE PROCEDURE
CREATE or alter PROCEDURE CreateMovie
    @Title NVARCHAR(255),
	@PubDate NVARCHAR(90),
    @OriginalTitle NVARCHAR(255),
    @Description NVARCHAR(MAX),
    @Duration INT,
    @Year INT,
    @Poster NVARCHAR(MAX),
    @Link NVARCHAR(MAX),
    @Reservation NVARCHAR(MAX),
    @DisplayDate NVARCHAR(90),
    @Performances NVARCHAR(MAX),
    @Trailer NVARCHAR(255),
    @ID INT OUTPUT
AS
BEGIN
    IF NOT EXISTS (SELECT * FROM Movie WHERE Title = @Title AND Year = @Year AND DisplayDate = @DisplayDate)
    BEGIN
        INSERT INTO Movie(Title, PubDate, OriginalTitle, Description, Duration, Year, Poster, Link, Reservation, DisplayDate, Performances, Trailer)
        VALUES (@Title,@PubDate, @OriginalTitle, @Description, @Duration, @Year, @Poster, @Link, @Reservation, @DisplayDate, @Performances, @Trailer)
        
        SET @ID = SCOPE_IDENTITY()
    END
    ELSE
    BEGIN
        SELECT @ID = ID FROM Movie WHERE Title = @Title AND Year = @Year AND DisplayDate = @DisplayDate
    END
END
GO



-- UPDATE PROCEDURE
CREATE or alter PROCEDURE UpdateMovie
    @ID INT,
	@PubDate NVARCHAR(90),
    @Title NVARCHAR(255),
    @OriginalTitle NVARCHAR(255),
    @Description NVARCHAR(MAX),
    @Duration INT,
    @Year INT,
    @Poster NVARCHAR(MAX),
    @Link NVARCHAR(MAX),
    @Reservation NVARCHAR(MAX),
    @DisplayDate NVARCHAR(90),
    @Performances NVARCHAR(MAX),
    @Trailer NVARCHAR(255)
AS
BEGIN
    UPDATE Movie
    SET Title = @Title,
		PubDate = @PubDate,
        OriginalTitle = @OriginalTitle,
        Description = @Description,
        Duration = @Duration,
        Year = @Year,
        Poster = @Poster,
        Link = @Link,
        Reservation = @Reservation,
        DisplayDate = @DisplayDate,
        Performances = @Performances,
        Trailer = @Trailer
    WHERE ID = @ID
END
GO


-- DELETE PROCEDURE
CREATE or alter PROCEDURE DeleteMovie
    @ID INT
AS
BEGIN
    -- Delete rows in referencing tables first
    DELETE FROM MovieGenre
    WHERE MovieID = @ID;

    DELETE FROM MoviePersonRole
    WHERE MovieID = @ID;

    DELETE FROM FavoriteMovie
    WHERE MovieID = @ID;

    -- Then delete the movie
    DELETE FROM Movie
    WHERE ID = @ID;
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

CREATE or alter PROCEDURE CreateGenre
    @Name NVARCHAR(255),
    @ID INT OUTPUT
AS
BEGIN
    IF NOT EXISTS (SELECT * FROM Genre WHERE Name = @Name)
    BEGIN
        INSERT INTO Genre(Name)
        VALUES (@Name)

        SET @ID = SCOPE_IDENTITY()
    END
    ELSE
    BEGIN
        SELECT @ID = ID FROM Genre WHERE Name = @Name
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
CREATE or alter PROCEDURE CreatePerson
    @Name NVARCHAR(255),
    @ID INT OUTPUT
AS
BEGIN
    IF NOT EXISTS (SELECT * FROM Person WHERE Name = @Name)
    BEGIN
        INSERT INTO Person(Name)
        VALUES (@Name)

        SET @ID = SCOPE_IDENTITY()
    END
    ELSE
    BEGIN
        SELECT @ID = ID FROM Person WHERE Name = @Name
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
CREATE or alter PROCEDURE CreateRole
    @Role NVARCHAR(255),
    @ID INT OUTPUT
AS
BEGIN
    IF NOT EXISTS (SELECT * FROM Role WHERE Role = @Role)
    BEGIN
        INSERT INTO Role(Role)
        VALUES (@Role)

        SET @ID = SCOPE_IDENTITY()
    END
    ELSE
    BEGIN
        SELECT @ID = ID FROM Role WHERE Role = @Role
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

---------------------- MovieGenre
----- CREATE PROCEDURE
CREATE or alter PROCEDURE CreateMovieGenre
    @MovieID INT,
    @GenreID INT,
    @ID INT OUTPUT
AS
BEGIN
    IF NOT EXISTS (SELECT * FROM MovieGenre WHERE MovieID = @MovieID AND GenreID = @GenreID)
    BEGIN
        INSERT INTO MovieGenre(MovieID, GenreID)
        VALUES (@MovieID, @GenreID)

        SET @ID = SCOPE_IDENTITY()
    END
    ELSE
    BEGIN
        SELECT @ID = ID FROM MovieGenre WHERE MovieID = @MovieID AND GenreID = @GenreID
    END
END
GO

----- UPDATE PROCEDURE
CREATE PROCEDURE UpdateMovieGenre
    @ID INT,
    @MovieID INT,
    @GenreID INT
AS
BEGIN
    UPDATE MovieGenre
    SET MovieID = @MovieID, GenreID = @GenreID
    WHERE ID = @ID
END
GO

----- DELETE PROCEDURE
CREATE or alter PROCEDURE DeleteMovieGenre
    @ID INT
AS
BEGIN
    DELETE FROM MovieGenre WHERE MovieID = @ID
END
GO

----- SELECT PROCEDURE
CREATE PROCEDURE SelectMovieGenre
    @ID INT
AS
BEGIN
    SELECT * FROM MovieGenre WHERE ID = @ID
END
GO

CREATE PROCEDURE SelectMovieGenres
AS
BEGIN
    SELECT * FROM MovieGenre
END
GO


---------------------- MoviePersonRole
----- CREATE PROCEDURE
CREATE or alter PROCEDURE CreateMoviePersonRole
    @MovieID INT,
    @PersonID INT,
    @RoleID INT,
    @ID INT OUTPUT
AS
BEGIN
    IF NOT EXISTS (SELECT * FROM MoviePersonRole WHERE MovieID = @MovieID AND PersonID = @PersonID AND RoleID = @RoleID)
    BEGIN
        INSERT INTO MoviePersonRole(MovieID, PersonID, RoleID)
        VALUES (@MovieID, @PersonID, @RoleID)

        SET @ID = SCOPE_IDENTITY()
    END
    ELSE
    BEGIN
        SELECT @ID = ID FROM MoviePersonRole WHERE MovieID = @MovieID AND PersonID = @PersonID AND RoleID = @RoleID
    END
END
GO

----- UPDATE PROCEDURE
CREATE PROCEDURE UpdateMoviePersonRole
    @ID INT,
    @MovieID INT,
    @PersonID INT,
    @RoleID INT
AS
BEGIN
    UPDATE MoviePersonRole
    SET MovieID = @MovieID, PersonID = @PersonID, RoleID = @RoleID
    WHERE ID = @ID
END
GO

----- DELETE PROCEDURE
CREATE or alter PROCEDURE DeleteMoviePersonRole
    @ID INT
AS
BEGIN
    DELETE FROM MoviePersonRole WHERE MovieID = @ID
END
GO

----- SELECT PROCEDURE
CREATE PROCEDURE SelectMoviePersonRole
    @ID INT
AS
BEGIN
    SELECT * FROM MoviePersonRole WHERE ID = @ID
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

---------------------- FavoriteMovie
----- CREATE PROCEDURE
CREATE or alter PROCEDURE CreateFavoriteMovie
    @UserID INT,
    @MovieID INT,
    @ID INT OUTPUT
AS
BEGIN
    IF NOT EXISTS (SELECT * FROM FavoriteMovie WHERE UserID = @UserID AND MovieID = @MovieID)
    BEGIN
        INSERT INTO FavoriteMovie(UserID, MovieID)
        VALUES (@UserID, @MovieID)

        SET @ID = SCOPE_IDENTITY()
    END
    ELSE
    BEGIN
        SELECT @ID = ID FROM FavoriteMovie WHERE UserID = @UserID AND MovieID = @MovieID
    END
END
GO

----- UPDATE PROCEDURE
CREATE PROCEDURE UpdateFavoriteMovie
    @ID INT,
    @UserID INT,
    @MovieID INT
AS
BEGIN
    UPDATE FavoriteMovie
    SET UserID = @UserID, MovieID = @MovieID
    WHERE ID = @ID
END
GO

----- DELETE PROCEDURE
CREATE PROCEDURE DeleteFavoriteMovie
    @ID INT
AS
BEGIN
    DELETE FROM FavoriteMovie WHERE ID = @ID
END
GO

----- SELECT PROCEDURE
CREATE PROCEDURE SelectFavoriteMovie
    @ID INT
AS
BEGIN
    SELECT * FROM FavoriteMovie WHERE ID = @ID
END
GO

CREATE PROCEDURE SelectFavoriteMovies
AS
BEGIN
    SELECT * FROM FavoriteMovie
END
GO

CREATE or alter PROCEDURE ClearTables
AS
BEGIN
    -- Turn off constraint checking to prevent foreign key issues when deleting
    EXEC sp_MSforeachtable "ALTER TABLE ? NOCHECK CONSTRAINT all";

    DELETE FROM FavoriteMovie;
    DELETE FROM MoviePersonRole;
    DELETE FROM MovieGenre;
    DELETE FROM Person;
    DELETE FROM Movie;
    DELETE FROM Genre;
    
    -- Turn constraint checking back on
    EXEC sp_MSforeachtable "ALTER TABLE ? WITH CHECK CHECK CONSTRAINT all";
END;

EXEC ClearTables
-- Jun 23 2023 12:00PM